$(document).ready(function () {
    // Cache the table selector
    const tableSelector = $('#tableSelector');

    // Initially hide all tables
    $('.dataTable').hide();

    // Show and initialize the default table
    let tableId = tableSelector.val();
    $('#' + tableId).show();

    // On table selection change
    tableSelector.on('change', function ()  {
        tableId = $(this).val();

        // Hide all tables
        $('.dataTable').hide();

        // Show the selected table
        $('#' + tableId).show();
        console.log(tableId);
    });

    let leaseType = '[[${leaseType}}]]';

    $('#tableSearch').on('keyup', function (){
        let value = $(this).val();
        console.log(value)

        let data = filterFunction(value, leaseType);

        rebuildTable(data);

        function filterFunction(value, data) {
            let filteredData= [];
            for(let i = 0; i < data.length; i++) {
                value = value.toLowerCase();
                let test = data[i].toLowerCase();
                console.log(test);

                if(test.includes(value)) {
                    filteredData.push(data[i])
                }
            }
            return filteredData;
        }

        function rebuildTable(data) {
            let table = document.getElementById('lease_table');
            table.innerHTML='';
            for (let i = 0; i < data.length; i++) {
                let row = `
                <tr>
                    <td>${data[i].vehicle_id}</td>
                    <td>${data[i].customerId}</td>
                    <td>${data[i].lease_type}</td>
                    <td>${data[i].lease_price}</td>
                    <td>${data[i].lease_start_date}</td>
                    <td>${data[i].lease_end_date}</td>
                    <td>${data[i].return_location}</td>
                </tr>`;
                table.innerHTML += row;
            }
        }
    })
});

