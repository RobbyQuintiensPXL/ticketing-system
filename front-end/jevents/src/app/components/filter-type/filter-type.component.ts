import {Component, Input, Output, EventEmitter, OnInit} from '@angular/core';

@Component({
  selector: 'app-filter-type',
  templateUrl: './filter-type.component.html',
  styleUrls: ['./filter-type.component.css']
})
export class FilterTypeComponent implements OnInit {
  @Output() typeOutput = new EventEmitter<any>();
  typeInput = '';


  constructor() {
  }

  msgType(type: string) {
    this.typeOutput.emit(this.typeInput = type);
  }

  ngOnInit(): void {

  }

}
