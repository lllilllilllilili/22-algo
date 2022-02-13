function solution(input) {
    const inputList = input.split('\n').map(element => element.split(' '));

    //원소의 수
    const numberOfelement = Number(inputList[0][0]);

    //연산의 수
    const numberOfSamples = Number(inputList[0][1]);

    const root = [];
    for(let i = 0; i <= numberOfelement; i++){
        root[i] = i;
    }
    console.log(`원소들의 parent 초기상태 : ${root}`);

    let results = [];

    for(let i = 0; i < numberOfSamples; i++){
        const isSum = inputList[i+1][0];
        const a = inputList[i+1][1];
        const b = inputList[i+1][2];

        isSum === '0' ? union(a, b) : checkSameRoot(a,b);
    }

    function union (x, y) {
        const xRoot = findRoot(x);
        root[y] = xRoot;
    }

    function findRoot(x){
       return root[x] === x ? x : root[x] = findRoot(root[x]);
    }

    function checkSameRoot(x,y) {
        const isSameRoot = root[x] === root[y] ? 'YES' : 'NO';
        results.push(isSameRoot);
    }
    return results.join('\n');
};

const input = '7 8\n' +
    '0 1 3\n' +
    '1 1 7\n' +
    '0 7 6\n' +
    '1 7 1\n' +
    '0 3 7\n' +
    '0 4 2\n' +
    '0 1 1\n' +
    '1 1 1';
const result = solution(input);
console.log(result)
