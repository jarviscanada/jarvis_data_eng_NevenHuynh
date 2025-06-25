class arrayMax {

  constructor(array) {
    this.array = array;
  }

  getMax() {
    let max = this.array[0];
    for(let i = 0; i < this.array.length; i++){
      if(this.array[i] > max)
        max = this.array[i];
    }
    return max;
  }
}

function main(){
  const arr = new arrayMax([1, 2, 3, 4, 5]);
  console.log(arr.getMax());
}

main();