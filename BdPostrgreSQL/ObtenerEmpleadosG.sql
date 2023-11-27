CREATE OR REPLACE PROCEDURE ObtenerEmpleadoDesencriptado(
    IN p_IdEmpleado INT
)
AS $$
DECLARE
    v_RFCEncriptado BYTEA;
    v_RFCDesencriptado VARCHAR(200); 
    v_Nombre VARCHAR(50);
    v_Edad INT;
    v_Telefono VARCHAR(10);
    v_CURP VARCHAR(18);
    v_NSS CHAR(11);
    v_Puesto VARCHAR(100);
    v_IDSucursal INT;
BEGIN
    
    SELECT RFC, Nombre, Edad, Telefono, CURP, NSS, Puesto, IDSucursal
    INTO v_RFCEncriptado, v_Nombre, v_Edad, v_Telefono, v_CURP, v_NSS, v_Puesto, v_IDSucursal
    FROM Empleado
    WHERE IdEmpleado = p_IdEmpleado;
	
    SELECT pgp_sym_decrypt(v_RFCEncriptado, 'R0bert541') INTO v_RFCDesencriptado;

    RAISE NOTICE 'IdEmpleado: %, Nombre: %, Edad: %, RFC Desencriptado: %, Telefono: %, CURP: %, NSS: %, Puesto: %, IDSucursal: %',
                 p_IdEmpleado, v_Nombre, v_Edad, v_RFCDesencriptado, v_Telefono, v_CURP, v_NSS, v_Puesto, v_IDSucursal;
END $$ LANGUAGE plpgsql;