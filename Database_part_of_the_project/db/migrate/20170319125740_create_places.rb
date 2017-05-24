class CreatePlaces < ActiveRecord::Migration
  def change
    create_table :places do |t|
      t.string :name
      t.string :continent
			t.string :country
			t.string :web_info
      t.float :longitude
      t.float :latitude
	

      t.timestamps null: false
    end
  end
end
