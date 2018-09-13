import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'searchContent'
})
export class SearchContentPipe implements PipeTransform {
  searchValue: string = '';

  transform(value: any, args?: any): any {
    if(!value) return [];
    if(!args) return value;

    console.log(args);
    const searchParam = args[0].toLowerCase();

    if (new String(searchParam).valueOf() == new String("Search By Task").valueOf()) {
      this.searchValue = args[1] === 'task' ? 'task' : '';
      return value.filter(val => {
        return val[this.searchValue].toLowerCase().includes(searchParam);
      });
    } else if (new String(searchParam).valueOf() == new String("Search By Parent Task").valueOf()) {
      this.searchValue = args[1] === 'task' ? 'parentTask' : '';
      return value.filter(val => {
        return val[this.searchValue].toLowerCase().includes(searchParam);
      });
    }
  }

}
