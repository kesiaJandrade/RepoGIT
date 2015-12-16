package k3.chadebeber17.DataBase;

/**
 * Created by Kesia on 10/12/2015.
 */
public class ScriptDataBase  {

    public static String getCreateCerveja()
    {



        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS Cerveja ( ");
        sqlBuilder.append("_id            INTEGER  NOT NULL ");
        sqlBuilder.append("PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("marca          VARCHAR (100) NOT NULL, ");
        sqlBuilder.append("tipo           VARCHAR (100) NOT NULL, ");
        sqlBuilder.append("descricao      VARCHAR (140), ");
        sqlBuilder.append("alcool         VARCHAR (10), ");
        sqlBuilder.append("quantidade     VARCHAR (10), ");
        sqlBuilder.append("localCompra    VARCHAR (140), ");
        sqlBuilder.append("foto           BLOB, ");
        sqlBuilder.append("caminhoFoto      VARCHAR (200), ");
        sqlBuilder.append("diretorioFoto    VARCHAR (200), ");
        sqlBuilder.append("voto    INTEGER (5) ");
        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }

    /*public static String getCreateVoto()
    {

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS Cerveja ( ");
        sqlBuilder.append("_idVoto            INTEGER  NOT NULL ");
        sqlBuilder.append("PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("idCerveja INTEGER REFERENCES Cerveja (id), ");
        sqlBuilder.append("voto VARCHAR (5) ");
        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }*/




}
