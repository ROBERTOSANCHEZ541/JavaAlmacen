CREATE OR REPLACE PROCEDURE ObtenerClientesF(IN p_IdCliente INT)
AS $$
BEGIN
    SELECT IdCliente, Nombre, Email, Telefono
    FROM Cliente
    WHERE IdCliente = p_IdCliente;
END $$ LANGUAGE plpgsql;
