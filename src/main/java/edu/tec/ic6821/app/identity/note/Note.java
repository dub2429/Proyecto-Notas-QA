package edu.tec.ic6821.app.identity.note;

import edu.tec.ic6821.app.identity.model.User;

import java.util.Objects;
import java.util.Optional;

public class Note {

    private final Optional<Long> id;
    private final String title;
    private final String content;
    private final long userId;

    public Note(Long id, String title, String content, long userId) {
        this.id = Optional.of(id);
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public Note(String title, String content, long userId) {
        this.userId = userId;
        this.id = Optional.empty();
        this.title = title;
        this.content = content;
    }

    public Optional<Long> getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getTitle(), getContent());
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        Note note = (Note) obj;
        return Objects.equals(getId(), note.getId()) &&
                Objects.equals(getTitle(), note.getTitle()) &&
                Objects.equals(getContent(), note.getContent());
    }

}

