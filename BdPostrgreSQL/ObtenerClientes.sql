CREATE OR REPLACE PROCEDURE ObtenerClientes()
AS $$
BEGIN
    SELECT IdCliente, Nombre, Email, Telefono
    FROM Cliente;
END $$ LANGUAGE plpgsql;
