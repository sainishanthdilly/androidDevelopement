package com.example.nishanth.midtermprep;

/**
 * Created by nishanth on 2/27/2017.
 */

public class NotesPojo {
    long id;
    String NoteTask;
    boolean checked2;
    String Priority;
    int PriSort;
    String timeNote;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPriSort() {
        return PriSort;
    }

    public void setPriSort(int priSort) {
        PriSort = priSort;
    }

    public NotesPojo() {
        checked2=false;
        PriSort=0;
    }

    public String getNoteTask() {
        return NoteTask;
    }

    public void setNoteTask(String noteTask) {
        NoteTask = noteTask;
    }

    public boolean isChecked() {
        return checked2;
    }

    public void setChecked(boolean checked) {
        this.checked2 = checked;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public String getTimeNote() {
        return timeNote;
    }

    public void setTimeNote(String timeNote) {
        this.timeNote = timeNote;
    }
}
