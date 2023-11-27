CREATE OR REPLACE PROCEDURE ObtenerDetallesPedido()
AS $$
BEGIN
    SELECT DetalleID, PedidoID, ProductoID, IDTransporte, Cantidad, PrecioUnitario
    FROM DetallesPedido;
END $$ LANGUAGE plpgsql;
