import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { IAirport } from 'src/app/interfaces/iairport';
import { ICity } from 'src/app/interfaces/icity';
import { IHotel } from 'src/app/interfaces/ihotel';
import { ITour } from 'src/app/interfaces/itour';
import { AirportService } from 'src/app/services/airport.service';
import { CityService } from 'src/app/services/city.service';
import { HotelService } from 'src/app/services/hotel.service';
import { TourService } from 'src/app/services/tour.service';
import { TourlistComponent } from '../tourlist/tourlist.component';

@Component({
  selector: 'app-edit-tour',
  templateUrl: './edit-tour.component.html',
  styleUrls: ['./edit-tour.component.css'],
})
export class EditTourComponent implements OnInit {
  id!: number;
  tour!: ITour;
  hotel: IHotel[] = [];
  form!: FormGroup;

  cities: ICity[] = [];
  airports: IAirport[] = [];
  hotels: IHotel[] = [];

  messegerValidator = '';

  constructor(
    public tourService: TourService,
    private route: ActivatedRoute,
    private router: Router,
    private cityService: CityService,
    private airportService: AirportService,
    private hotelService: HotelService
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.tourService.getById(this.id).subscribe((data: ITour) => {
      this.tour = data;
    });
    this.form = new FormGroup({
      id: new FormControl('', [Validators.required]),
      departureDate: new FormControl('', [Validators.required]),
      dateOfReturn: new FormControl('', [Validators.required]),
      numberOfDays: new FormControl('', [Validators.required]),
      priceAdult: new FormControl('', [Validators.required]),
      priceChildren: new FormControl('', [Validators.required]),
      promoted: new FormControl('', [Validators.required]),
      numberSeatAdult: new FormControl('', [Validators.required]),
      numberSeatChildren: new FormControl('', [Validators.required]),

      departureCity: new FormControl('', [Validators.required]),
      arrivalCity: new FormControl('', [Validators.required]),
      departureAirport: new FormControl('', [Validators.required]),
      arrivalAirport: new FormControl('', [Validators.required]),
      hotel: new FormControl('', [Validators.required]),
    });
  

    this.showAllAirport();
    this.showAllCities();
    this.showAllHotel();
  }

  submit() {
    console.log(this.form.value);
    this.tourService.updateTour(this.form.value).subscribe((res) => {
      console.log('Tour updated successfully!');
      this.router.navigateByUrl('/tours');
    });
  }

  showAllCities() {
    this.cityService.getAllCities().subscribe((data: any) => {
      (this.cities = data), console.log(this.cities);
    });
  }

  showAllAirport() {
    this.airportService.getAllAirports().subscribe((data: any) => {
      (this.airports = data), console.log(this.airports);
    });
  }

  showAllHotel() {
    this.hotelService.getAllHotels().subscribe((data: any) => {
      (this.hotels = data), console.log(this.hotels);
    });
  }


  updateTour() {
    this.tourService.update(this.id, this.tour)
      .subscribe(data => {
        console.log(data);
       
      }, error => console.log(error));
  }

  updateTour1() {
    this.tourService.updateTour(this.tour)
      .subscribe(data => {
        console.log(data);
       
      }, error => console.log(error));
  }
  onSubmit() {
    this.updateTour();    
  }



}
