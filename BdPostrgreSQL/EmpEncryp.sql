select*from empleado

CREATE EXTENSION IF NOT EXISTS pgcrypto;

CALL ObtenerEmpleadoDesencriptado(8);

CREATE OR REPLACE PROCEDURE InsertarEmpleadoEncriptado(
    IN p_Nombre VARCHAR(50),
    IN p_Edad INT,
    IN p_RFC VARCHAR(200),
    IN p_Telefono VARCHAR(10),
    IN p_CURP VARCHAR(18),
    IN p_NSS CHAR(11),
    IN p_Puesto VARCHAR(100),
    IN p_IDSucursal INT
)
AS $$
DECLARE
    v_RFCEncriptado BYTEA;
BEGIN
    
    v_RFCEncriptado := pgp_sym_encrypt(p_RFC, 'R0bert541');

    INSERT INTO Empleado (Nombre, Edad, RFC, Telefono, CURP, NSS, Puesto, IDSucursal)
    VALUES (p_Nombre, p_Edad, v_RFCEncriptado, p_Telefono, p_CURP, p_NSS, p_Puesto, p_IDSucursal);
END $$ LANGUAGE plpgsql;

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

CALL InsertarEmpleadoEncriptado(
    'NombreEmpleado',
    25,
    'RFC123456',
    '1234567890',
    'CURP12345678901234',
    'NSS12345678',
    'Empleado',
    1
);


