function addIngresoToTable(){
    const codigo = document.getElementById("ingreso_codigo_prod").value;
    const productoSelect = document.getElementById("producto_ingreso");
    const proveedorSelect = document.getElementById("proveedor_ingreso");
    const unidadSelect = document.getElementById("unidad_ingreso");
    const almacenSelect = document.getElementById("almacen_ingreso");
    const stock = parseInt(document.getElementById("ingreso_stock").value);
    const descripcion = document.getElementById("ingreso_desc").value;
        
    nombreProducto = productoSelect.options[productoSelect.selectedIndex].text;
    nombreProveedor = proveedorSelect.options[proveedorSelect.selectedIndex].text;
    nombreUnidad = unidadSelect.options[unidadSelect.selectedIndex].text;
    nombreAlmacen = almacenSelect.options[almacenSelect.selectedIndex].text; 

    const tablaProductos = document.getElementById("tableIngreso");
    const tablaBody = tablaProductos.getElementsByTagName("tbody")[0];
        
    const fila = document.createElement("tr");
    fila.innerHTML = `
        <td><input type="hidden" name="codigo[]" value="${codigo}">${codigo}</td>
        <td><input type="hidden" name="prod_id[]" value="${productoSelect.value}">${nombreProducto}</td>
        <td><input type="hidden" name="alm_id[]" value="${almacenSelect.value}">${nombreAlmacen}</td>
        <td><input type="hidden" name="prov_id[]" value="${proveedorSelect.value}">${nombreProveedor}</td>
        <td><input type="hidden" name="uni_id[]" value="${unidadSelect.value}">${nombreUnidad}</td>
        <td><input type="hidden" name="cantidad[]" value="${stock}">${stock}</td>
        <td><input type="hidden" name="descripcion[]" value="${descripcion}">${descripcion}</td>
        <td>
        <button type="button" class="btn btn-warning"><i class='bx bxs-edit bx-xs'></i></button>
        <button type="button" class="btn btn-danger"><i class='bx bxs-trash bx-xs'></i></button>
        </td>
    `;
    tablaBody.appendChild(fila);
}
