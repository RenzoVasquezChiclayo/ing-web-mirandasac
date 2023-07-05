let productList = [];
        let totalPrice = 0;

        let productosAgregados = [];

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
                
            const subtotal = precio * cantidad;
            if (productosAgregados.filter(p=>{p == productoId}).length > 0) {
                alert("El producto ya ha sido agregado");
                return;
            }
            alert(productosAgregados.filter(p=>{p == productoId})); 
            productosAgregados.push(productoId);
                
            const tablaProductos = document.getElementById("tableProductosEnvio");
            const tablaBody = tablaProductos.getElementsByTagName("tbody")[0];
                
            const fila = document.createElement("tr");
            fila.innerHTML = `
                <td>${productosAgregados.length}</td>
                <td>${producto.nombre}</td>
                <td>${producto.descripcion}</td>
                <td>${cantidad}</td>
                <td>${precio}</td>
                <td>${subtotal}</td>
                <td><button type="button" class="btn btn-danger">Eliminar</button></td>
            `;
            tablaBody.appendChild(fila);
        }

        function getTotal(subtotal){
            totalPrice += subtotal;
            document.getElementById("total_envio").value = totalPrice;
        }