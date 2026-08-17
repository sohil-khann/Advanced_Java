import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.concurrent.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Scheduled {
    long fixedRate() default 0;
    String cron() default "";
    long initialDelay() default 0;
}

class TaskScheduler {
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(0);

    void scheduleTasks(Object target) {
        Class<?> clazz = target.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Scheduled.class)) {
                Scheduled scheduled = method.getAnnotation(Scheduled.class);
                method.setAccessible(true);
                Runnable task = () -> {
                    try {
                        method.invoke(target);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                };

                long initialDelay = scheduled.initialDelay();
                long period = scheduled.fixedRate();
                String cron = scheduled.cron();

                if (!cron.isEmpty()) {
                    scheduleCron(task, cron, initialDelay);
                } else if (period > 0) {
                    executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.MILLISECONDS);
                } else {
                    System.out.println("Invalid @Scheduled config on: " + method.getName());
                }
            }
        }
    }

    private void scheduleCron(Runnable task, String cron, long initialDelay) {
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(task, initialDelay, 1, TimeUnit.SECONDS);
        new Thread(() -> {
            while (!future.isDone()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
        System.out.println("Cron scheduling started for: " + cron);
    }

    void shutdown() {
        executor.shutdown();
    }
}

class DemoService {
    @Scheduled(fixedRate = 2000)
    public void heartbeat() {
        System.out.println("[heartbeat] Running at " + new Date());
    }

    @Scheduled(cron = "0 * * * * *")
    public void hourlyTick() {
        System.out.println("[hourlyTick] Running at " + new Date());
    }

    @Scheduled(fixedRate = 5000, initialDelay = 1000)
    public void periodicTask() {
        System.out.println("[periodicTask] Running at " + new Date());
    }
}

class TaskSchedulerDemo {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        DemoService service = new DemoService();
        scheduler.scheduleTasks(service);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nShutting down scheduler...");
            scheduler.shutdown();
        }));

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        scheduler.shutdown();
        System.out.println("Scheduler stopped.");
    }
}