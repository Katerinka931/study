package com.ssau.study.repository.jdbc;

import com.ssau.study.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcStudentRepository implements StudentRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private RowMapper<Student> studentMapper = (rs, rowNum) -> {
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setName(rs.getString("name"));
        student.setBirthdate(rs.getDate("birthdate"));
        student.setNumber(rs.getInt("number"));
        return student;
    };

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from public.students", Integer.class);
    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query("select * from public.students", studentMapper);
    }

    @Override
    public List<Student> findAllByName(String name) {
        return namedParameterJdbcTemplate.query("select * from public.students where name ilike '%' || :name || '%'",
                Collections.singletonMap("name", name), studentMapper);
    }

    @Override
    public List<Student> findByID(long id) {
        return namedParameterJdbcTemplate.query("select * from public.students where id = :id",
                Collections.singletonMap("id", id), studentMapper);
    }

    @Override
    public void deleteById(long id) {
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        namedParameterJdbcTemplate.update("delete from public.students where id = :id", params);
    }

    @Override
    public List<Student> createStudent(String name, Date birthdate, int number) {
        var params = new MapSqlParameterSource();
        params.addValue("name", name);
        params.addValue("birthdate", birthdate);
        params.addValue("number", number);

        return namedParameterJdbcTemplate.query("insert into public.students (name, birthdate, number) values (:name, :birthdate, :number) returning id, name, birthdate, number",
                params, studentMapper);
    }

    @Override
    public int updateStudent(long id, String name, Date birthdate, int number) {
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("name", name);
        params.addValue("birthdate", birthdate);
        params.addValue("number", number);

        return namedParameterJdbcTemplate.update("update public.students set name = :name, birthdate = :birthdate, number = :number where id = :id", params);

//        return namedParameterJdbcTemplate.query("update public.students set name = :name, birthdate = :birthdate, number = :number where id = :id returning id, name, birthdate, number",
//                params, studentMapper);
    }
}
