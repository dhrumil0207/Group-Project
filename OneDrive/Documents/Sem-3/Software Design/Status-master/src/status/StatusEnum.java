/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package status;

/**
 * MidTerm-Exam
 *
 * @author srinivsi
 */
public enum StatusEnum {
    REJECTED, PENDING, PROCESSING, APPROVED, COMPLETED;

    /**
     * Method to get StatusEnum from a string input.
     *
     * @param code The string representation of the status number.
     * @return Corresponding StatusEnum or null if invalid.
     */
    public static StatusEnum fromString(String code) {
        switch (code.toUpperCase()) {
            case "ZERO":
                return REJECTED;
            case "ONE":
                return PENDING;
            case "TWO":
                return PROCESSING;
            case "THREE":
                return APPROVED;
            case "FOUR":
                return COMPLETED; // New status
            default:
                return null;
        }
    }
}
