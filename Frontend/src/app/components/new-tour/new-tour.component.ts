import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { IAirport } from 'src/app/interfaces/iairport';
import { ICity } from 'src/app/interfaces/icity';
import { IHotel } from 'src/app/interfaces/ihotel';
import { AirportService } from 'src/app/services/airport.service';
import { CityService } from 'src/app/services/city.service';
import { HotelService } from 'src/app/services/hotel.service';
import { TourService } from 'src/app/services/tour.service';

@Component({
  selector: 'app-new-tour',
  templateUrl: './new-tour.component.html',
  styleUrls: ['./new-tour.component.css'],
})
export class NewTourComponent implements OnInit {
  cities: ICity[] = [];
  airports: IAirport[] = [];
  hotels: IHotel[] = [];

  tourForm!: FormGroup;

  messegerValidator = '';

  constructor(
    private tourService: TourService,
    private airportService: AirportService,
    private cityService: CityService,
    private hotelService: HotelService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.newTourForm();
    this.showAllCities();
    this.showAllAirport();
    this.showAllHotel();
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

  newTourForm() {
    this.tourForm = new FormGroup({
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
  }

  submitTour() {
    if (this.tourForm.valid) {
      this.tourService.addTour(this.tourForm.value).subscribe(
        (data: any) => {
          this.messegerValidator = ' Your tour form has been submitted';
          //reset - pt a se reseta formularul (campuri albe)
          this.tourForm.reset();
          //dupa butonul de submit sa se duca pe pagina de AlltOURS
          this.router.navigate(['/tours']);
        },
        (err: any) => console.log(err)
      );
    } else {
      this.messegerValidator =
        'Please fill out the tour form before submiting!';
    }
  }
}
