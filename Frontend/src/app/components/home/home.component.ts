import { isDataSource } from '@angular/cdk/collections';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ITour } from 'src/app/interfaces/itour';
import { TourService } from 'src/app/services/tour.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  tours: ITour[]=[];
  tour!: ITour;
  selectedValue = null;
  constructor(private tourService: TourService) {
  }

  ngOnInit(): void {
    // this.getAllTours();
    // this.getTourPromoted();
  }

  public getAllTours(): void {
    this.tourService.getAllTours().subscribe(
      (response: ITour[]) => {
        this.tours = response;
        console.log(this.tours);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getTourPromoted(): void{
    this.tourService.getTourPromoted().subscribe(
      (response: ITour[]) => {
        this.tours = response;
        console.log(this.tours);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
