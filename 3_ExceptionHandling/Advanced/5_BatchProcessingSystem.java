import java.util.ArrayList;
import java.util.List;

interface RecordProcessor<T> {
    void process(T record) throws Exception;
}

class ProcessingResult<T> {
    T record;
    boolean success;
    String errorMessage;

    public ProcessingResult(T record, boolean success, String errorMessage) {
        this.record = record;
        this.success = success;
        this.errorMessage = errorMessage;
    }
}

class BatchProcessor<T> {
    private List<String> failedRecords;

    public BatchProcessor() {
        this.failedRecords = new ArrayList<>();
    }

    public List<ProcessingResult<T>> processBatch(List<T> records, RecordProcessor<T> processor) {
        List<ProcessingResult<T>> results = new ArrayList<>();

        for (T record : records) {
            try {
                processor.process(record);
                results.add(new ProcessingResult<>(record, true, null));
                System.out.println("Processed: " + record);
            } catch (Exception e) {
                String errorMsg = "Failed to process " + record + ": " + e.getMessage();
                failedRecords.add(errorMsg);
                results.add(new ProcessingResult<>(record, false, errorMsg));
                System.out.println("Error: " + errorMsg);
            }
        }

        return results;
    }

    public void printFailedRecords() {
        System.out.println("\n=== Failed Records ===");
        if (failedRecords.isEmpty()) {
            System.out.println("No failed records.");
        } else {
            for (String record : failedRecords) {
                System.out.println(record);
            }
        }
    }
}

class StringProcessor implements RecordProcessor<String> {
    @Override
    public void process(String record) throws Exception {
        if (record == null || record.trim().isEmpty()) {
            throw new IllegalArgumentException("Empty or null record");
        }
        if (record.equals("FAIL")) {
            throw new RuntimeException("Simulated processing failure");
        }
        if (record.equals("ERROR")) {
            throw new RuntimeException("Simulated system error");
        }
        Thread.sleep(50);
    }
}

class BatchProcessingSystem {
    public static void main(String[] args) {
        List<String> records = new ArrayList<>();
        records.add("Record-001");
        records.add("Record-002");
        records.add("FAIL");
        records.add("Record-004");
        records.add("ERROR");
        records.add("Record-006");
        records.add(null);
        records.add("Record-008");

        BatchProcessor<String> processor = new BatchProcessor<>();
        List<ProcessingResult<String>> results = processor.processBatch(records, new StringProcessor());

        long successCount = results.stream().filter(r -> r.success).count();
        long failureCount = results.stream().filter(r -> !r.success).count();

        System.out.println("\n=== Summary ===");
        System.out.println("Total: " + results.size());
        System.out.println("Success: " + successCount);
        System.out.println("Failed: " + failureCount);

        processor.printFailedRecords();
    }
}
