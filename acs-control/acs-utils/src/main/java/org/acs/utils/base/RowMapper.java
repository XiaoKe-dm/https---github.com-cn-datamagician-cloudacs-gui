package org.acs.utils.base;

import java.sql.ResultSet;
import java.sql.SQLException;
 
/**
 * RowMapper
 * @author 柯晓
 */
public interface RowMapper
{
    public Object mapRow(ResultSet rs, int index)
        throws SQLException;
}