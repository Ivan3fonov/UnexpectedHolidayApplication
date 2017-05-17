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
			if params.has_key?(:continent)
				@place = Place.where(continent: params[:continent])
			else
				@place=Place.all	
			end
			
			prng = Random.new
			num = prng.rand(@place.count)
			render json: @place[num]
	end

end
