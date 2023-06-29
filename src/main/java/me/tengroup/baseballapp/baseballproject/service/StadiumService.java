package me.tengroup.baseballapp.baseballproject.service;

import lombok.RequiredArgsConstructor;
import me.tengroup.baseballapp.baseballproject.data.db.DBConnection;
import me.tengroup.baseballapp.baseballproject.model.stadium.Stadium;
import me.tengroup.baseballapp.baseballproject.model.stadium.StadiumDAO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StadiumService {
    private Connection connection = DBConnection.getInstance();
    private StadiumDAO stadiumDAO = new StadiumDAO(connection);

    public int createStadium(String name) throws SQLException {
        return stadiumDAO.createStaduim(name);
    }

    public List<Stadium> AllStadiumList() throws SQLException{
        return stadiumDAO.getAllStadium();
    }
}
