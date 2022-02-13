function solution(input){

    const inputList = input.split('\n').map(ele => ele.split(' '));

    //집이 2개인 경우
    if(inputList.length === 2) return inputList[1][2];

    const numberOfHouse = Number(inputList[0][0]);
    const numberOfRoutes = Number(inputList[0][1]);

    //route 정보 빼고 제거
    inputList.shift();
    const transformed = inputList.map(ele => ({x: ele[0], y: ele[1], cost: Number(ele[2])}));
    const routesByIncrease = transformed.sort((a, b) => Number(a.cost) - Number(b.cost));

    const parent = [];
    //index 가 0이 면 1번 집
    for(let i = 1; i <= numberOfHouse; i++){
        parent[i] = i;
    }

    let minRouteCost = [];

    for(let routeInfo of routesByIncrease){
        //x,y는 연결하고 싶은 집 번호
        const x = routeInfo.x;
        const y = routeInfo.y;
        const cost = routeInfo.cost;
        union(x, y, cost);
    }

    function union(x, y, cost) {
        const rootX = findRoot(x);
        console.log(`${x}의 root ${rootX}`);
        const rootY = findRoot(y);
        console.log(`${y}의 현재 root ${rootY}`);

        if(rootX !== rootY){
            parent[y] = x;
            minRouteCost.push(cost);
        }
        console.log(`${y}의 변경된 root ${findRoot(y)}`);
    }

    function findRoot(x) {
        return parent[x] == x ? x : findRoot(parent[x]);
    }
    return minRouteCost.slice(0, minRouteCost.length-1).reduce((sum, current) => sum + current, 0);
}

const input = '7 12\n' +
    '1 2 3\n' +
    '1 3 2\n' +
    '3 2 1\n' +
    '2 5 2\n' +
    '3 4 4\n' +
    '7 3 6\n' +
    '5 1 5\n' +
    '1 6 2\n' +
    '6 4 1\n' +
    '6 5 3\n' +
    '4 5 3\n' +
    '6 7 4';

const result = solution(input);
console.log(result)