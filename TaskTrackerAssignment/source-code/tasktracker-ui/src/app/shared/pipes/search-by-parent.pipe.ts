import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'searchByParent'
})
export class SearchByParentPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    if(!value) return [];
    if(!args) return value;

    console.log(args);
    const searchParam = args[0].toLowerCase();
    const taskName = args[1] === 'task' ? 'task' : '';

    return value.filter(val => {
      return val[taskName].toLowerCase().includes(searchParam);
    });
  }

}
