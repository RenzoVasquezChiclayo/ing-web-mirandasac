let productList = [];
        let totalPrice = 0;

        let productosAgregados = new Set();

        document.addEventListener("DOMContentLoaded", function() {
            // Obtener los productos del modelo y convertirlos a un objeto JavaScript
            
            productos.forEach(producto => {
                    const newProduct = {
                        id:producto.id,
                        nombre:producto.nombre,
                        descripcion:producto.descripcion
                    };
                    productList.push(newProduct);
                });
        });

        function addProductToTable(){
            const productoSelect = document.getElementById("producto_envio");
            const cantidad = parseInt(document.getElementById("cantidad_producto").value);
            const precio = parseInt(document.getElementById("precio_producto").value);
                
            const productoId = parseInt(productoSelect.value);
                
            const producto = productList.find(p => p.id === productoId);

            const newAgregado = {id:producto.id, precio: precio, cantidad: cantidad};
                
            const subtotal = precio * cantidad;
            if ([...productosAgregados].some(obj => obj.id === productoId)) {
                alert("El producto ya ha sido agregado");
                return null;
            }
            productosAgregados.add(newAgregado);

            const tablaProductos = document.getElementById("tableProductosEnvio");
            const tablaBody = tablaProductos.getElementsByTagName("tbody")[0];
                
            const fila = document.createElement("tr");
            fila.innerHTML = `
                <td>${productosAgregados.size}</td>
                <td><input type="hidden" name="prod_id[]" value="${producto.id}">${producto.id}</td>
                <td><input type="hidden" name="prod_nombre[]" value="${producto.nombre}">${producto.nombre}</td>
                <td><input type="hidden" name="prod_descripcion[]" value="${producto.descripcion}">${producto.descripcion}</td>
                <td><input type="hidden" name="cantidad[]" value="${cantidad}">${cantidad}</td>
                <td><input type="hidden" name="precio[]" value="${precio}">${precio}</td>
                <td>${subtotal}</td>
                <td>
                <button type="button" class="btn btn-warning"><i class='bx bxs-edit bx-xs'></i></button>
                <button type="button" class="btn btn-danger"><i class='bx bxs-trash bx-xs'></i></button>
                </td>
            `;
            tablaBody.appendChild(fila);
            getTotal(subtotal);
        }

        function getTotal(subtotal){
            totalPrice += subtotal;
            document.getElementById("total_envio").value = totalPrice;
        }

        function cleanFields(){

            document.getElementById("cantidad_producto").value="0";
            document.getElementById("precio_producto").value="0";
        }

        function sendData(){
            document.getElementById("field_data").value = productosAgregados;
        }

        function validarIngreso(){
            ingresoCheck = document.getElementById("ingreso_envio");
            div_ingreso = document.getElementById("div_ingreso");
            select_ingreso = document.getElementById("select_ingreso");
            if (ingresoCheck.checked) {
                select_ingreso.hidden = false;
                div_ingreso.hidden = true;
            }else{
                select_ingreso.hidden = true;
                div_ingreso.hidden = false;
            }
        }