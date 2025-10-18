
import { Component, OnInit } from "@angular/core";
import { Cricketer } from "../../types/Cricketer";

@Component({
    selector : 'app-cricketerarray',
    // standalone :true,
    templateUrl:'./cricketerarray.component.html',
    styleUrls :['./cricketerarray.component.scss']
})
export class CricketerArrayComponent implements OnInit {

    cricketers : Cricketer[] =[];

    showCricketers : boolean =false;


    ngOnInit()
    {
      this.cricketers.push(new Cricketer(1,1,'Virat Kohli',35,'Indian',17,'batsman',4000,25));
      this.cricketers.push(new Cricketer(2,2,'AB de Villiers',36,'South africa',18,'batsman',4500,90));
      this.cricketers.push(new Cricketer(3,3,'Gayle',46,'West Indies',29,'batsman',5000,60));
      this.cricketers.push(new Cricketer(4,4,'Abhishek',22,'Indian',5,'batsman',2500,20));



    }
    toggleCricketers()
    {
        this.showCricketers = !this.showCricketers
    }
 

}
