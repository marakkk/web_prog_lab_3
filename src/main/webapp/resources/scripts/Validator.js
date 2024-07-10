function checkR(){
        let checkboxList = document.querySelectorAll("input[type=checkbox]");
        let count = 0;
        checkboxList.forEach(function (checkbox) {
            if (checkbox.checked) {
                count++;
            }
        });
        if (count === 1){
            return true;
        }else if (count === 0) {
            alert("Выберите R")
            disableButtons(true);
            return false;
        } else if (count > 0) {
            alert("Выберите только один R");
            disableButtons(true);
            return false;
        }

}

function isValid(x, y, r){
    return (x >= -2 && x <= 1.5 && y >= -3 && y <= 3 && r >= 1 && r <= 5 && r % 0.5 === 0.);
}