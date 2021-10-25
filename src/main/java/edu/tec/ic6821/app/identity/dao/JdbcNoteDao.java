package edu.tec.ic6821.app.identity.dao;

import edu.tec.ic6821.app.identity.note.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Component
public class JdbcNoteDao extends JdbcDaoSupport implements NoteDao {

    @Autowired
    public JdbcNoteDao(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public Note create(Note note) {
        String sql = "insert into note (title, content, user_id) values (?, ?, ?)";

        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, note.getTitle());
            ps.setString(2, note.getContent());
            ps.setLong(3,note.getUserId());
            return ps;
        }, holder);

        return new Note(holder.getKey().longValue(), note.getTitle(), note.getContent(), note.getUserId());
    }

    @Override
    public void editTitle(Note note, String title){
        String sql = "update note set title = ? where user_id = ?";
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, title);
            ps.setLong(2, note.getUserId());
            return ps;
        });
    }

    @Override
    public void editContent(Note note, String content){
        String sql = "update note set content = ? where user_id = ?";
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, content);
            ps.setLong(2, note.getUserId());
            return ps;
        });
    }

    @Override
    public Boolean existsByTitle(String title) {
        String sql = "select count(id) from note where title = ?";

        Long count = getJdbcTemplate().queryForObject(sql,
                new Object[]{title},
                Long.class);

        return count >= 1;
    }

    @Override
    public void deleteNote(String title){
        String sql = "delete from note where title = ?";
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,title);
            return ps;
        });
    }

}
