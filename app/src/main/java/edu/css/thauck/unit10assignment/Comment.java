package edu.css.thauck.unit10assignment;

/**
 * Stores comment to and reads comment from database.
 * Fields will match the fields in the DB table.
 */
public class Comment {
    private long id;            // database record id
    private String comment;     // random comment

    /**
     * Get column ID
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Set column ID
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get random comment
     * @return
     */
    public String getComment() {
        return comment;
    }

    /**
     * Set random comment
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return comment;
    }
}
