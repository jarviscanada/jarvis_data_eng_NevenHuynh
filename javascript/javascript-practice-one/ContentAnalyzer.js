class ContentAnalyzer {
  constructor(content)  {
    this.content = content;
   }

  analyze() {
    if (this.isHTML()){
      return {
        contentType: "HTML",
        tags: this.countMatches(/<\/?([A-Za-z0-9]+)[^>]*>/g)
      };
    } else if (this.isCSS())  {
      return {
        contentType: "CSS",
        cssTargets: this.countMatches(/[.#]?[a-zA-Z0-9]+\s*{[^}]*}/g)
      };
    } else {
      return {
        contentType: "TEXT",
        lineNumber: this.countLines()
      };
    }
  }

  // Check if string matches HTML regex
  isHTML(){
    return /<\/?([A-Za-z0-9]+)[^>]*>/.test(this.content);
  }

  // Check if string matches CSS regex
  isCSS(){
    return /[.#]?[a-zA-Z0-9]+\s*{[^}]*}/.test(this.content);
  }

  countMatches(regex) {
    const count = {};
    let match;
    while ((match = regex.exec(this.content)) !== null) {
      const key = match[1];
      counts[key] = (counts[key] || 0) + 1;
    }
    return counts;
  }

  countLines() {
    return this.content.split('\n').length;
  }
}

  // Main function wrapper
  function analyzeContent(content) {
    const analyzer = new ContentAnalyzer(content);
    return analyzer.analyze();
  }

  console.log(analyzeContent("this is a test\nSeems to work"));

  console.log(analyzeContent("body{blabla} a{color:#fff} a{ padding:0"));

  console.log(analyzeContent("<html><div></div><div></div></html>"));
