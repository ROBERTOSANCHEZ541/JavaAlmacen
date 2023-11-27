CREATE OR REPLACE PROCEDURE ObtenerTransportes()
AS $$
BEGIN
    SELECT IDTransporte, Nombre, Tipo, CapacidadKg, NumeroPlaca
    FROM Transporte;
END $$ LANGUAGE plpgsql;
