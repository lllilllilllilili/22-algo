function solution(input){

    const listInput = input.split('\n').map(element => element.split(' '));

    const numberOfComs = Number(listInput[0]);
    const numberOfLines = Number(listInput[1]);

    let countVirusedCom = 0;

    const root = [];

    for(let i = 0; i < numberOfComs; i ++){
        root[i] = i+1;
    }

    for(let i = 0; i < numberOfLines; i++){
        const x = listInput[i+2][0];
        const y = listInput[i+2][1];
        console.log(`${x}와 ${y}컴퓨터 연결`);
        union(x, y);
        console.log("연결 후 연결되었는지 확인")
        console.log(`${x} 컴퓨터의 루트 ${root[x-1]}`);
        console.log(`${y} 컴퓨터의 루트 ${root[y-1]}`);
    }

    function union(x, y) {
        const xRoot = findRoot(x);
        root[y-1] = xRoot;
    }

    function findRoot(x){
        if(root[x-1] == x){
            return x;
        }else {
            root[x-1] = findRoot(root[x-1]);
            return root[x-1];
        }
    }
    const root1Com = root[0];
    console.log(`1번 컴퓨터의 루트: ${root1Com}`)
    for(let i = 1; i < numberOfComs; i++){
        console.log(`${i+1} 컴퓨터의 루트 ${root[i]}`)

        root[i] == root1Com && countVirusedCom++;
    }
    return countVirusedCom ;
}

const input = '7\n' +
    '6\n' +
    '1 2\n' +
    '2 3\n' +
    '1 5\n' +
    '5 2\n' +
    '5 6\n' +
    '4 7';
const result = solution(input);
console.log(result);