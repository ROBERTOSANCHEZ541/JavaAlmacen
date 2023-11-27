 	CREATE OR REPLACE PROCEDURE ObtenerProveedores()
AS $$
BEGIN
    SELECT IdProveedor, Nombre, Telefono, Direccion, RFC
    FROM Proveedor;
END $$ LANGUAGE plpgsql;
