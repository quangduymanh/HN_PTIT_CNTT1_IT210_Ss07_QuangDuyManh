#1. Lỗi tên cửa hàng bị null
   HTML:
   <input type="text" name="restaurantName" />
   Model:
   private String name;

Nguyên nhân:
Tên name trong HTML không trùng với field name trong model (restaurantName ≠ name) nên Spring không bind được dữ liệu.

Kết quả:
Giá trị name bị null.

#2. Tại sao checkbox active không hoạt động đúng?
   HTML:
   <input type="checkbox" name="active" value="MỞ_CỬA" />
   Model:
   private boolean active;

Nguyên nhân:

Giá trị "MỞ_CỬA" không phải true/false nên không convert được sang boolean
Checkbox không được chọn sẽ không gửi dữ liệu lên server
Không có input hidden nên không đảm bảo giá trị false

Kết luận:
Checkbox không hoạt động đúng do sai kiểu dữ liệu và thiếu cơ chế xử lý khi không chọn.

#3. Cách khắc phục
   <input type="text" name="name" />

<input type="hidden" name="active" value="false" />
<input type="checkbox" name="active" value="true" />