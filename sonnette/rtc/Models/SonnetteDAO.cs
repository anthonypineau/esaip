using System.Data.SqlClient;

namespace sonnette.rtc.Models
{
    public class SonnetteDAO
    {
        public static List<Sonnette> GetAll()
        {
            List<Sonnette> Sonnettes = new List<Sonnette>();
            using (SqlConnection conn = new SqlConnection())
            {
                conn.ConnectionString = "Server=localhost;Database=sonnette;Trusted_Connection=true";

                conn.Open();

                SqlCommand command = new SqlCommand("SELECT * FROM sonnettes", conn);

                using (SqlDataReader reader = command.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        Sonnette Sonnette = new Sonnette();
                        Sonnette.Id = (int)reader[1];
                        Sonnette.Date = (DateTime)reader[2];
                        Sonnette.TypeAppui = (int)reader[3];
                        Sonnettes.Add(Sonnette);
                    }
                }

                conn.Close();
            }
            return Sonnettes;
        }

        public static void Insert(Sonnette sonnette)
        {
            using (SqlConnection conn = new SqlConnection())
            {
                conn.ConnectionString = "Server=localhost:3306;Database=sonnette;User=root";

                conn.Open();

                SqlCommand insertCommand = new SqlCommand("INSERT INTO TableName (idSonnette, date, typeAppui) VALUES (@0, @1, @2)", conn);

                insertCommand.Parameters.Add(new SqlParameter("0", sonnette.Id));
                insertCommand.Parameters.Add(new SqlParameter("1", sonnette.Date));
                insertCommand.Parameters.Add(new SqlParameter("2", sonnette.TypeAppui));

                insertCommand.ExecuteNonQuery();

                conn.Close();
            }
        }
    }
}
