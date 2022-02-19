let fs = require('fs');
// var input = fs.readFileSync('/dev/stdin').toString().split(' ');

function solution(input) {

    const numbers = Number(4);
    const pick = Number(2);
    const nList = [];
    for(let i = 0; i < numbers; i++){
        nList[i] = i+1;
    }

    const result = getSequence(pick, nList);

    function getSequence(pick, numbers) {

        if(pick === 1) return numbers.map(ele => [ele]);
        const result = [];
        numbers.forEach((fixed, index, numbers) => {
            const rest = numbers.filter(ele => ele !== fixed);
            const restPermuList = getSequence(pick-1, rest);
            restPermuList.forEach(each =>
                result.push([fixed, ...each])
            );
        });
        return result;
    }
    result.forEach(each => console.log(each.join(' ')));
}
solution();
