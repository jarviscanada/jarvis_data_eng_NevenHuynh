class commonElement{

  constructor (array){
  this.array = array;
  }

  countMostCommonElement(){
  const map = new Map();

  for(let i = 0; i < this.array.length; i++){
    if(!map.has(this.array[i])){
      map.set(this.array[i], 1);
    } else {
      map.set(this.array[i], map.get(this.array[i]) + 1);
    }
  }

  let mostCommonElement = 0;
  for (const [key, value] of map){
      if(value > mostCommonElement)
        mostCommonElement = value;
    }
    return mostCommonElement;
  }
}

function main(){
  const array = new commonElement([ 1, 5, -3, 2, -3, 1, 1, -3 ]);
  console.log(array.countMostCommonElement());

  const array2 = new commonElement(["cat", "cat", "dog", "chicken", "cat", "dog", "cat"]);
  console.log(array2.countMostCommonElement());
}
main();