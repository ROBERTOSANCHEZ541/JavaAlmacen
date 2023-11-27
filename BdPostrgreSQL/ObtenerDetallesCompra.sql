CREATE OR REPLACE PROCEDURE ObtenerDetallesCompra()
AS $$
BEGIN
    SELECT DetalleCompraID, CompraID, ProductoID, IDTransporte, Cantidad, PrecioUnitario
    FROM DetallesCompra;
END $$ LANGUAGE plpgsql;
