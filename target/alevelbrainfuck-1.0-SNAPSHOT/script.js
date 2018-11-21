
var input = document.getElementById("input");
var output = document.getElementById("output");
var convert = document.getElementById("convert");

convert.addEventListener("click", function (event) {
    axios.get('convert', {
        params: {
            text:input.value
        }
    })
        .then(function (result) {
            var ans = result.data.answer;
            document.getElementById("output").value = ans;

        })
        .catch(function (reason) {
            if (reason.response.status==500){

            }

        });
});