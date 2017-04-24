class PlacesController < ApplicationController
	skip_before_filter :verify_authenticity_token

	def create
		if !Place.exists?(params[:name])
			@place= Place.new
			@place.name=params[:name]
			@place.continent = params[:continent]
			@place.longitude=params[:longitude]
			@place.latitude=params[:latitude]
			@place.save
			render json: @place
		end
	end

	def show
			@place=Place.where(name: params[:name],continent: params[:continent])	
			render json: @place
	end
end
