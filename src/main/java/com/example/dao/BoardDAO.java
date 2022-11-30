package com.example.dao;

import com.example.bean.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BoardDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String BOARD_INSERT = "insert into BOARD (category, title, writer, content, photo) values (?,?,?,?,?)";
    private final String BOARD_UPDATE = "update BOARD set category=?, title=?, writer=?, content=?, photo=? where seq=?";
    private final String BOARD_DELETE = "delete from BOARD  where seq=?";
    private final String BOARD_GET = "select * from BOARD  where seq=?";
    private final String BOARD_LIST = "select * from BOARD order by seq desc";

    public int insertBoard(BoardVO vo) {
        System.out.println("===> JDBC로 insertBoard() 기능 처리");
        String sql = "insert into BOARD (category, title, writer, content, photo) values ("
                + "'" + vo.getCategory() + "', "
                + "'" + vo.getTitle() + "', "
                + "'" + vo.getWriter() + "', "
                + "'" + vo.getContent() + "', "
                + "'" + vo.getPhoto() + "')";
        return jdbcTemplate.update(sql);
    }

    // 글 삭제
    public int deleteBoard(int seq) {
        System.out.println("===> JDBC로 deleteBoard() 기능 처리");
        String sql = "delete from BOARD where seq = " + seq;
        return jdbcTemplate.update(sql);
    }
    public int updateBoard(BoardVO vo) {
        System.out.println("===> JDBC로 updateBoard() 기능 처리");
        String sql = "update BOARD set title='" + vo.getTitle() + "', "
                + "category= '" + vo.getCategory() + "', "
                + "title= '" + vo.getTitle() + "', "
                + "writer= '" + vo.getWriter() + "', "
                + "content= '" + vo.getContent() + "', "
                + "photo= '" + vo.getPhoto() + "' where swq=" + vo.getSeq();
        return jdbcTemplate.update(sql);
    }

    public BoardVO getBoard(int seq) {
        String sql = "select * from BOARD where seq=" + seq;
        return jdbcTemplate.queryForObject(sql, new BoardRowMapper());
    }

//    public String getPhotoFilename(int seq) {
//        String filename = null;
//        try {
//            conn = JDBCUtil.getConnection();
//            stmt = conn.prepareStatement(BOARD_GET);
//            stmt.setInt(1, seq);
//            rs = stmt.executeQuery();
//            if(rs.next()) {
//                filename = rs.getString("photo");
//            }
//            rs.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("===> JDBC로 getPhotoFilename() 기능 처리");
//        return filename;
//    }

    public List<BoardVO> getBoardList(){
        String sql = "select * from BOARD order by regdate desc";
        return jdbcTemplate.query(sql, new BoardRowMapper());
    }
}