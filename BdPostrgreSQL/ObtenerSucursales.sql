CREATE OR REPLACE PROCEDURE ObtenerSucursales()
AS $$
BEGIN
    SELECT IDSucursal, NombreSucursal, Direccion, Ciudad, CodigoPostal, Telefono
    FROM Sucursales;
END $$ LANGUAGE plpgsql;
