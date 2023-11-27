CREATE OR REPLACE PROCEDURE ObtenerProductos()
AS $$
BEGIN
    SELECT 
        IdArticulo,
        NombreProducto,
        Precio,
        Stock,
        Descripcion,
        ID_Categoria,
        IDSucursal
    FROM Productos;
END $$ LANGUAGE plpgsql;
