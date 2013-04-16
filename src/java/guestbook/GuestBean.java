// GuestBean.java
// Bean for interacting with the AddressBook database
package guestbook;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

@ManagedBean( name="guestBean" )
public class GuestBean
{
   // instance variables that represent one address
   private Date  date;
   private String name;
   private String email;
   private String message;

   // allow the server to inject the DataSource
   @Resource( name="jdbc/guestbook" )
   DataSource dataSource;

    public Date getDate() {
        date = new Date();
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
  
   
   // save a new address book entry
   public String save() throws SQLException
   {
      // check whether dataSource was injected by the server
      if ( dataSource == null )
         throw new SQLException( "Unable to obtain DataSource" );

      // obtain a connection from the connection pool
      Connection connection = dataSource.getConnection();
      
      // check whether connection was successful
      if ( connection == null )
         throw new SQLException( "Unable to connect to DataSource" );

      try
      {
         // create a PreparedStatement to insert a new address book entry
         PreparedStatement addEntry =
            connection.prepareStatement( "INSERT INTO MESSAGES " +
               "(DATE,NAME,EMAIL,MESSAGE)" +
               "VALUES ( ?, ?, ?, ?)" );

         // specify the PreparedStatement's arguments
         java.sql.Date sqlDate = new java.sql.Date(getDate().getTime());
         
         addEntry.setDate( 1, sqlDate );
         addEntry.setString( 2, getName() );
         addEntry.setString( 3, getEmail() );
         addEntry.setString( 4, getMessage() );

         addEntry.executeUpdate(); // insert the entry
         return "index"; // go back to index.xhtml page
      } // end try
      finally
      {
         connection.close(); // return this connection to pool
      } // end finally
   } // end method save

   // return a ResultSet of entries
   public ResultSet getGuests() throws SQLException
   {
      // check whether dataSource was injected by the server
      if ( dataSource == null )
         throw new SQLException( "Unable to obtain DataSource" );

      // obtain a connection from the connection pool
      Connection connection = dataSource.getConnection();

      // check whether connection was successful
      if ( connection == null )
         throw new SQLException( "Unable to connect to DataSource" );

      try
      {
         // create a PreparedStatement to insert a new address book entry
         PreparedStatement getAddresses = connection.prepareStatement(
            "SELECT DATE, NAME, EMAIL, MESSAGE " +
            "FROM MESSAGES");

         CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
         rowSet.populate( getAddresses.executeQuery() );
         return rowSet; 
      } // end try
      finally
      {
         connection.close(); // return this connection to pool
      } // end finally
   } // end method getAddresses
} // end class GuestBean




