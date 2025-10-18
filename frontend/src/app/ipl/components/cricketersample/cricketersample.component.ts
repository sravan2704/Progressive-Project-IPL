import { Component } from "@angular/core";
import { Cricketer } from "../../types/Cricketer";

@Component({
   selector : 'app-cricketersample',
   standalone :true,
   templateUrl:'./cricketersample.component.html',
   styleUrls :['./cricketersample.component.scss']
})
export class CricketerSampleComponent {
   cricketer = new Cricketer(1,1,"Virat",45,"Indian",25,"Btasman",250,11);
}
