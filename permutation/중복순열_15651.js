function solution(input) {
    input = input.split(' ');
    const numbers = Number(input[0]);
    const pick = Number(input[1]);
    const nList = [];

    for(let i = 0; i < numbers; i++){
        nList[i] = i + 1;
    }

    const result = getPermutation(pick, nList);

    function getPermutation(pick, nList) {
        if(pick <= 1) return nList.map(number => [number])
        const result = [];

        nList.forEach((fixed, index, nList) => {

            const permutation = getPermutation(pick - 1, nList);
            const permus = permutation.map(each => [fixed, ...each])
            result.push(...permus);

        });
        return result;
    }

    result.forEach(current => {
        console.log(current.join(' '))
    })

}
const input = '6 6';
console.time("중복순열 수행시간")
solution(input);
console.timeEnd("중복순열 수행시간")



