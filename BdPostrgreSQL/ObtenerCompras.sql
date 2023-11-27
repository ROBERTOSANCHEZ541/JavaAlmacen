CREATE OR REPLACE PROCEDURE ObtenerCompras()
AS $$
BEGIN
    SELECT CompraID, FechaCompra, ProveedorID, EmpleadoID
    FROM Compras;
END $$ LANGUAGE plpgsql;
