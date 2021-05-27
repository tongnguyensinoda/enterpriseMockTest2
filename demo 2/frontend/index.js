function get() {
    document.querySelector("#table").innerHTML = ``
    fetch('http://localhost:8080/product/list')
        .then(response => response.json())
        .then(json => {
            console.log('Success:', json);
            for (let i = 0; i < json.length; i++) {

                var productId = json[i].productId
                var productName = json[i].productName
                var manufacturer = json[i].manufacturer.manufacturerName
                var country = json[i].country.countryName
                var brand = json[i].brand


                document.querySelector("#table").innerHTML += `
                <tr name="product">
                    <td>
                        <input type="text" name="productID" value="${productId}">
                    </td>
                    <td>
                        <input type="text" name="productName" value="${productName}">
                    </td>
                    <td>
                        <input type="text" name="brand" value="${brand}">
                    </td>
                    <td>
                        <input type="text" name="manufacturer" value="${manufacturer}">
                    </td>
                    <td>
                        <input type="text" name="country" value="${country}">
                    </td>
                </tr>
                `;
                // sessionStorage.setItem("")
            }

        }

        )
}

function post() {
    var table = document.getElementsByClassName("table");
    var t = table[0];
    for (var r = 0; r < t.rows.length; r++) {
        var productId = t.rows[r].cells[0].getElementsByTagName('input')[0].value;
        var productName = t.rows[r].cells[1].getElementsByTagName('input')[0].value;
        var brand = t.rows[r].cells[2].getElementsByTagName('input')[0].value;

        var requestURL = new URL("http://localhost:8080/product/update/".concat(productId));

        requestURL.searchParams.append("productName", productName);
        requestURL.searchParams.append("brand", brand);

        console.log(requestURL)

        fetch(requestURL, {
            method: 'PUT'
            // headers: {
            //     'content-type': 'application/json',
            // },
            // body: JSON.stringify({
            //     // "productId": productId,
            //     "productName": productName,
            //     "brand": brand
            // })
        })
            .then(response => response.json())
            .then(data => {
                console.log(data.message);
            })
            .catch(err => {
                console.log(err)
            })
        // Headers
    }

    // for (let i = 0; i < allProducts.length; i++) {
    // var productId = document.getElementsByName("product").getElementByTagName("td")[i]

    // var productName = document.querySelector("#productName").value
    // var manufacturer = document.querySelector("#brand").value
    // var country = document.querySelector("#manufacturer").value
    // var brand = document.querySelector("#country").value
    // }
    // var productId = document.querySelector("#productID").value
    // var productName = document.querySelector("#productName").value
    // var manufacturer = document.querySelector("#brand").value
    // var country = document.querySelector("#manufacturer").value
    // var brand = document.querySelector("#country").value

    // fetch()
}

