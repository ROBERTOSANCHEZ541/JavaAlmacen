CREATE OR REPLACE PROCEDURE ObtenerPedidos()
AS $$
BEGIN
    SELECT PedidoID, FechaPedido, ClienteID
    FROM Pedidos;
END $$ LANGUAGE plpgsql;
