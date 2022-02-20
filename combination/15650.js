// let fs = require('fs');
// var input = fs.readFileSync('/dev/stdin').toString().split(' ');

function solution(input){
    input = input.split(' ');
    const numbers = Number(input[0]);
    const pick = Number(input[1]);

    const nList = [];
    for(let i = 1; i <= numbers; i ++){
        nList[i] = i;
    }

    const result = getCombination(pick, nList);

    function getCombination(pick, nList){

        if(pick === 1) return nList.map(number => [number]);

        const result = [];
        nList.forEach((fixed, index, nList) => {

            const rest = nList.slice(index + 1);
            const combination = getCombination(pick - 1, rest);
            combination.forEach(comb => {
                result.push([fixed, ...comb]);
            })
        })

        return result;
    }
    result.forEach(each => {
        console.log(each.join(' '))
    });
}

const input = '4 3';

solution(input);