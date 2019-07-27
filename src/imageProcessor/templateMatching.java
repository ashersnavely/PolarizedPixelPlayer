package imageProcessor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FastFeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.imgcodecs.Imgcodecs;

@SuppressWarnings("deprecation")
public class templateMatching {

    public static BufferedImage detectMatches(File subImage, File supImage) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        Mat subMat = Imgcodecs.imread(subImage.getAbsolutePath(), Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        Mat supMat = Imgcodecs.imread(supImage.getAbsolutePath(), Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

        if (subMat.empty() || supMat.empty()) {
            System.out.println(" --(!) Error reading images ");
            return null;
        }

        // -- Step 1: Detect the keypoints using SURF Detector
        //I am not sure where to use it
        //int minHessian = 400;

        FastFeatureDetector detector = FastFeatureDetector.create();

        MatOfKeyPoint subKeyPoints = new MatOfKeyPoint();
        MatOfKeyPoint supKeyPoints = new MatOfKeyPoint();

        detector.detect(subMat, subKeyPoints);
        detector.detect(supMat, supKeyPoints);

        // -- Step 2: Calculate descriptors (feature vectors)
        //Feature2D extractors = new Feature2D(DescriptorExtractor.SURF);
        //BOWImgDescriptorExtractor extractor = new BOWImgDescriptorExtractor();
        DescriptorExtractor subExtractor = DescriptorExtractor.create(DescriptorExtractor.SURF);
        DescriptorExtractor supExtractor = DescriptorExtractor.create(DescriptorExtractor.SURF);

        Mat subDescriptors = new Mat();
        Mat supDescriptors = new Mat();

        subExtractor.compute(subMat, subKeyPoints, subDescriptors);
        supExtractor.compute(supMat, supKeyPoints, supDescriptors);

        // -- Step 3: Matching descriptor vectors using FLANN matcher
        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorExtractor.SURF);
        MatOfDMatch matches = new MatOfDMatch();
        matcher.match(subDescriptors, supDescriptors, matches);
        DMatch[] matchesArr = matches.toArray();

        double maxDist = 0;
        double minDist = 100;

        // -- Quick calculation of max and min distances between keypoints
        for (int i = 0; i < matchesArr.length; i++) {
            double dist = matchesArr[i].distance;
            if (dist < minDist)
                minDist = dist;
            if (dist > maxDist)
                maxDist = dist;
        }

        System.out.printf("-- Max dist : %f \n", maxDist);
        System.out.printf("-- Min dist : %f \n", minDist);

        // -- Draw only "good" matches (i.e. whose distance is less than
        // 2*min_dist,
        // -- or a small arbitary value ( 0.02 ) in the event that min_dist is
        // very
        // -- small)
        // -- PS.- radiusMatch can also be used here.
        MatOfDMatch good_matches = new MatOfDMatch();

        for (int i = 0; i < matchesArr.length; i++) {
            if (matchesArr[i].distance <= Math.max(2 * minDist, 0.02)) {
                good_matches.push_back(matches.row(i));
            }
        }

        // -- Draw only "good" matches
        Mat imgMatches = new Mat();
        Features2d.drawMatches(subMat, subKeyPoints, supMat, supKeyPoints, good_matches, imgMatches);
        //, Scalar.all(-1), Scalar.all(-1),
                //null, Features2d.NOT_DRAW_SINGLE_POINTS);

        // ----Here i had to Patch around a little----
        MatOfByte matOfByte = new MatOfByte();
        
        
        Imgcodecs.imencode(".jpg", imgMatches, matOfByte);

        byte[] byteArray = matOfByte.toArray();
        BufferedImage bufImage = null;
        try {

            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        for (int i = 0; i < (int) good_matches.rows(); i++) {
            System.out.printf(
                    "-- Good Match [%d] Keypoint 1: %d  -- Keypoint 2: %d  \n",
                    i, good_matches.toArray()[i].queryIdx,
                    good_matches.toArray()[i].trainIdx);
        }

        return bufImage;

    }
}
