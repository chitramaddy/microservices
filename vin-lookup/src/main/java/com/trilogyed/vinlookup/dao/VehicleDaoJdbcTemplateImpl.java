package com.trilogyed.vinlookup.dao;

import com.trilogyed.vinlookup.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VehicleDaoJdbcTemplateImpl implements VehicleDao {

    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_VEHICLE_SQL =
            "select * from vehicle where vin = ?";

    private static final String SELECT_ALL_VEHICLES_SQL =
            "select * from vehicle";

    private static final String INSERT_VEHICLE_SQL =
            "insert into vehicle (vin, type, make, model, year, color) values (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_VEHICLE_SQL =
            "update vehicle set type = ?, make = ?, model = ?, year = ?, color = ? where vin = ?";

    private static final String DELETE_VEHICLE_SQL =
            "delete from vehicle where vin = ?";

    @Autowired
    public VehicleDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Vehicle getVehicle(String vin) {
        try {

            return jdbcTemplate.queryForObject(SELECT_VEHICLE_SQL, this::mapRowToVehicle, vin);

        } catch (EmptyResultDataAccessException e) {
            // if nothing is returned just catch the exception and return null
            return null;
        }
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return jdbcTemplate.query(SELECT_ALL_VEHICLES_SQL, this::mapRowToVehicle);
    }

    @Override
    @Transactional
    public Vehicle addVehicle(Vehicle vehicle) {
        jdbcTemplate.update(INSERT_VEHICLE_SQL,
                vehicle.getVin(),
                vehicle.getType(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getYear(),
                vehicle.getColor());

//        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
//        System.out.println(vehicle);

        return vehicle;
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {

        jdbcTemplate.update(UPDATE_VEHICLE_SQL,

                vehicle.getType(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getYear(),
                vehicle.getColor(),
                vehicle.getVin());

    }

    @Override
    public void deleteVehicle(String vin) {
        jdbcTemplate.update(DELETE_VEHICLE_SQL, vin);

    }

    private Vehicle mapRowToVehicle(ResultSet rs, int rowNum) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(rs.getString("vin"));
        vehicle.setType(rs.getString("type"));
        vehicle.setMake(rs.getString("make"));
        vehicle.setModel(rs.getString("model"));
        vehicle.setYear(rs.getString("year"));
        vehicle.setColor(rs.getString("color"));

        return vehicle;
    }


}
